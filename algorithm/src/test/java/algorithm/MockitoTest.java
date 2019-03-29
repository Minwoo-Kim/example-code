/**
 * 
 */
package algorithm;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import algorithm.codility.BinaryGap;

public class MockitoTest {

	@Mock
	BinaryGap binaryGap;

	@Test
	public void mockEx() {
		// Active MokitoAnnotations
		MockitoAnnotations.initMocks(this);

		BinaryGap bg1 = this.binaryGap;
		BinaryGap bg2 = mock(BinaryGap.class);

		assertTrue(bg1 != null && bg2 != null);

		System.out.println(bg1.hashCode());
		System.out.println(bg2.hashCode());
	}

	@Test
	public void whenThenEx() {
		List<User> list = new ArrayList<User>();

		User user = mock(User.class);
		when(user.getName()).thenReturn("Name").thenReturn("DDD");
		when(user.getEmail()).thenReturn("abc@email.com");
		when(user.getAge()).thenReturn(10);
		
		list.add(user);

		for (int i = 0; i < 100; i++) {
			User u = mock(User.class);
			
			u.setName(anyString());
			u.setEmail(anyString());
			u.setAge(anyInt());

			list.add(u);
		}

		list.stream().map(v -> v.getName()).forEach(System.out::println);
	}

	private class User {
		String name;
		String email;
		Integer age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}
	}
}
