package com.msauthentication.service;

import com.msauthentication.entity.Role;

public interface RoleService {

    Role findByName(String name);
}
