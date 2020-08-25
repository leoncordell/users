package com.dwp.users;

import com.dwp.users.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UsersApplicationTests {

	@Autowired
	private UserController controller;

	@Test
	public void contextLoads() {
		assertNotNull(controller, "UserController should be set");
	}

	@Test
	public void main() {
		UsersApplication.main(new String[]{});
	}


}
