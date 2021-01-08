package com.simpledev.redistest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	private final UserRepository userRepository;

	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@SuppressWarnings("unchecked")
	@Cacheable(value = "users", key = "#userId", unless = "#result.followers < 12000")
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable String userId) {
		  LOG.info("Getting user with ID {}.", userId);
		  return (User) userRepository.findById(Long.valueOf(userId)).orElse(new User());
		}

	@CachePut(value = "users", key = "#user.id")
	@PutMapping("/update")
	public User updatePersonByID(@RequestBody User user) {
		userRepository.save(user);
		return user;
	}
	
	@CacheEvict(value = "users", allEntries=true)
	@DeleteMapping("/{id}")
	public void deleteUserByID(@PathVariable Long id) {
	  LOG.info("deleting person with id {}", id);
	  userRepository.deleteById(id);
	}
}
