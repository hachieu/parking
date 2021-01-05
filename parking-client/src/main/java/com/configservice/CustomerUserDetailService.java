package com.configservice;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.entity.Role;
import com.entity.User;

@Service
public class CustomerUserDetailService implements UserDetailsService {
	private RestTemplate restTemplate = new RestTemplate();

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = restTemplate.getForObject("http://localhost:8080/user/find-email/{email}", User.class, username);
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		
		List<Role> roles = user.getRoles();
		if(roles!=null) {
			for(Role getRole : roles) {
				grantedAuthorities.add(new SimpleGrantedAuthority(getRole.getName()));
			}
		}
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
	}
	
}
