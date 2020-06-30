package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests() // 認証が必要となるURLを設定します
	    	.mvcMatchers("/login").permitAll()   // （）内のURLは認証不要
	    	.mvcMatchers("/inquiry/form","/inquiry/search").hasRole("USER") 
	    	
	    	.mvcMatchers("/inquiry/list").hasRole("ADMIN")
	    	
	        	.anyRequest().authenticated()// それ以外はすべて認証された状態じゃなきゃダメ
	    
	    .and()
        .formLogin() // ログインページに飛ばす
        	.loginPage("/login") // ログインページのURL
	    	.successForwardUrl("/success")//認証成功時のURL(フォワード先のURL)
	    	.failureUrl("/login-error")//認証失敗時のURL
            .permitAll()
            
            .and()
	    .logout()
        // ログアウト処理
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        // ログアウト成功時の遷移先
        .logoutSuccessUrl("/inquiry/form")
        // ログアウト時に削除するクッキー名
        .deleteCookies("JSESSIONID")
        // ログアウト時のセッション破棄を有効化
        .invalidateHttpSession(true)
        .permitAll();
//	        	http.logout().logoutSuccessUrl("/inquiry/form").permitAll();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		  auth.inMemoryAuthentication()
		      .withUser("user").password(encoder.encode("password")).roles("USER")
		      .and()
		      .withUser("admin").password(encoder.encode("adminpassword")).roles("ADMIN");
	}
	
	
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {    //指定したフォルダに対してはすべてのユーザのアクセスを許す
	    web.ignoring().antMatchers("/favicon.ico", "/css/**", "/js/**", "/images/**", "/fonts/**");
	}

}
