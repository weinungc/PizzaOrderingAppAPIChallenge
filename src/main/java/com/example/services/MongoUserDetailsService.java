package com.example.services;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import com.example.entity.User;
//import com.example.repository.UsersRepository;
//
//@Component
//public class MongoUserDetailsService implements UserDetailsService {
//	@Autowired
//	private UsersRepository repository;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = repository.findByUsername(username);
//		if (user == null) {
//			throw new UsernameNotFoundException("User not found");
//		}
//		List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));
//		return null;
//		//return new User(user.getUsername(), user.getPassword(), authorities);
//	}
//}
