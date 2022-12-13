package com.trebnikau.service;

import com.trebnikau.entity.ClientPassport;
import com.trebnikau.entity.Role;
import com.trebnikau.entity.User;
import com.trebnikau.repo.UserRepo;
import com.trebnikau.service.impl.MailSenderServiceImpl;
import com.trebnikau.threads.MailSenderThread;
import com.trebnikau.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import java.util.*;
import java.util.stream.Collectors;


@Service
public interface UserService {

    void saveUserAfterEditing(User user, String userName, Map<String, String> form);

    void addUserRoles(User user, Map<String, String> form);

    Set<String> getSetOfRoles();

    boolean isEditUserFormContainsRoles(Map<String, String> form);

    int addNewUser(User user);

    void save(User user);

    String saveUserPassport(User user, String type, ClientPassport clientPassport, BindingResult bindingResult, Model model);

    String saveUserUsername(User user, String newUsername, Model model);

    String saveUserPassword(User user, String currentPassword, String newPassword, String passwordConfirmation, Model model);

    String saveUserAfterChangingItByAdmin(User user, String username, Map<String, String> form, Model model);

    String showUserList(String filter, Model model);

    String showUserPassport(User user, Model model);

    String addRegisteredUser(String passwordConfirmation, User user, BindingResult bindingResult, Model model);

    String blockOrUnlockUser(User user, String typeOfRequest);

    String activateUser(String activationCode, Model model);
}
