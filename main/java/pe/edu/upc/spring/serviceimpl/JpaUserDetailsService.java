package pe.edu.upc.spring.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.repository.IUsuariooDao;
import pe.edu.upc.spring.model.Role;
import pe.edu.upc.spring.model.Usuarioo;


@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private IUsuariooDao usuarioDao;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuarioo usuarioo = usuarioDao.findByUsername(username);

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Role role : usuarioo.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}

		return new User(usuarioo.getUsername(), usuarioo.getPassword(), usuarioo.getEnabled(), true, true, true,
				authorities);
	}

}
