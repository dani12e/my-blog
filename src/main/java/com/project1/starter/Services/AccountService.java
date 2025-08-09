package com.project1.starter.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.project1.starter.Models.Account;
import com.project1.starter.Models.Authority;
import com.project1.starter.Repository.AccountRepository;
import com.project1.starter.util.constants.Authorities;
import com.project1.starter.util.constants.Roles;

@Service
public class AccountService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordencoder;

    public Account save(Account account) {
        account.setPassword(passwordencoder.encode(account.getPassword()));
        if (account.getRole() == null) {
            account.setRole(Roles.USER.getRole());
        }
        if (account.getPhoto() == null) {
            account.setPhoto("/images/client2.jpg");
        }
        
        // account.setGender(null); // or "Female", etc.
        // account.setAge("18");
       

        return accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> optionalaccount = accountRepository.findByEmail(email);
        if (!optionalaccount.isPresent()) {
            throw new UsernameNotFoundException("we couldn't find that user");
        }

        Account account = optionalaccount.get();

        List<GrantedAuthority> grantedAuthority = new ArrayList<>();
        grantedAuthority.add(new SimpleGrantedAuthority(account.getRole()));

        for (Authority _auths : account.getAuths()) {
            grantedAuthority.add(new SimpleGrantedAuthority(_auths.getName()));
        }
        return new User(account.getEmail(), account.getPassword(), grantedAuthority);
    }

    public Optional<Account> findOneByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

     public Optional<Account> findOneByid(Long id) {
        return accountRepository.findById(id);
    }

       public Optional<Account> findOneByToken(String token) {
        return accountRepository.findByToken(token);
    }

}
