package com.jdn.varieties.inventory_control.config.infraestructure;

import com.challenge.ecommerce.tps.encript.KeyRsaSupplier;
import com.challenge.ecommerce.tps.filter.AuthenticationFilter;
import com.challenge.ecommerce.tps.jwt.JwtManagement;
import com.jdn.varieties.inventory_control.users.application.login.AuthWithPasswordAndUsername;
import com.jdn.varieties.inventory_control.users.application.login.CreateAccessTokenJwt;
import com.jdn.varieties.inventory_control.users.domain.UserDomain;
import com.jdn.varieties.inventory_control.users.domain.UserRepository;
import com.jdn.varieties.inventory_control.users.infrastructure.UserEntity;
import com.jdn.varieties.inventory_control.users.infrastructure.UserModelMapper;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Value("${settings-jks.path}")
	private String pathJks;

	@Value("${settings-jks.password}")
	private String passwordJks;

	@Value("${settings-jks.alias}")
	private String aliasJks;

	@Value("${settings-refresh-token.times.jwt}")
	private Long expiryTimeAtMinutes;

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOriginPattern("*");
		configuration.addAllowedMethod("*");
		configuration.addAllowedHeader("*");
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public AuthenticationFilter authenticationFilter(JwtManagement jwtManagement) {
		List<String> excludeUrlPatterns = List.of("/**/user/login/**", "/**/refresh", "/**/swagger-ui/**",
				"/**/v3/api-docs/**");
		return AuthenticationFilter.builder().jwtManagement(jwtManagement).excludeUrlPatterns(excludeUrlPatterns)
				.build();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationProvider authenticationProvider,
			AuthenticationFilter authenticationFilter) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable).cors(cors -> cors.configurationSource(corsConfigurationSource()))
				.authorizeHttpRequests(authRequest -> authRequest
						.requestMatchers("/user/login/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll().anyRequest()
						.authenticated())
				.sessionManagement(
						sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		// TODO: guardar password ususario root vSg-m4g(1555
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepository) throws UsernameNotFoundException {
		return (username) -> {
			final UserDomain userDomain = userRepository.findByUsername(username).orElseThrow(
					() -> new UsernameNotFoundException("User with email not found in UserDetailsService"));
			return UserModelMapper.mapToUserEntity(userDomain);
		};
	}

	@Bean
	public AuthWithPasswordAndUsername authWithPasswordAndUsername(AuthenticationManager authenticationManager) {
		return (username, password) -> {
			final Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			return UserModelMapper.mapToUserDomain((UserEntity) authentication.getPrincipal());
		};
	}

	@Bean
	public CreateAccessTokenJwt createAccessTokenJwt(JwtManagement jwtManagement) {
		return jwtManagement::createToken;
	}

	@Bean
	public KeyRsaSupplier keyRsaSupplier()
			throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
		return new KeyRsaSupplier(this.passwordJks, this.aliasJks, this.pathJks);
	}

	@Primary
	@Bean
	public JwtManagement jwtManagement(KeyRsaSupplier keyRsaSupplier) {
		return new JwtManagement(keyRsaSupplier, this.expiryTimeAtMinutes);
	}
}
