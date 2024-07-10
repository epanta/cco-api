/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sabesp.cco.authentication.service;

import br.com.sabesp.cco.exceptions.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Properties;

/**
 *
 * @author Whitlock
 */
@Service
public class LdapService {

    @Autowired
    Environment environment;

    private DirContext context;

    private void connect() {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        props.put(Context.PROVIDER_URL, environment.getProperty("ldap.url") + environment.getProperty("ldap.base"));
        props.put(Context.SECURITY_AUTHENTICATION, "simple");
        props.put(Context.SECURITY_PRINCIPAL, environment.getProperty("ldap.username"));
        props.put(Context.SECURITY_CREDENTIALS, environment.getProperty("ldap.password"));

        try {
            context = new InitialDirContext(props);
        } catch (NamingException e) {
            throw new NegocioException("Erro ao conectar ao LDAP!");
        }
    }

    private DirContext connect(String password, String basepath) {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        props.put(Context.PROVIDER_URL, environment.getProperty("ldap.url"));
        props.put(Context.SECURITY_AUTHENTICATION, "simple");
        props.put(Context.SECURITY_PRINCIPAL, basepath);
        props.put(Context.SECURITY_CREDENTIALS, password);

        try {
            return new InitialDirContext(props);
        } catch (NamingException e) {
            if (e instanceof AuthenticationException) {
                throw new NegocioException("Credenciais inválidas!");
            }
            throw new NegocioException("Erro ao conectar ao LDAP!");
        }
    }

    public Boolean authenticateUser(Authentication authentication) {
        try {
            this.connect();

            SearchControls sc = new SearchControls();
            sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

            NamingEnumeration response = this.context.search("", "sAMAccountName=" + authentication.getPrincipal().toString(), sc);

            SearchResult usuario = null;

            while (response.hasMoreElements()) {
                usuario = (SearchResult) response.nextElement();
            }

            if (usuario != null) {
                Attributes att = usuario.getAttributes();
                String dn = (String) att.get("distinguishedName").get();
                DirContext userContext = this.connect(authentication.getCredentials().toString(), dn);
                if (!ObjectUtils.isEmpty(userContext)) {
                    return true;
                }
            } else {
                throw new NegocioException("Usuário não encontrado no LDAP!");
            }

            return false;
        } catch (NamingException e) {
            throw new NegocioException("Erro ao conectar no LDAP!");
        }
    }

}
