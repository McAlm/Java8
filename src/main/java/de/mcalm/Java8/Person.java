package de.mcalm.Java8;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Person {

	private @Getter String name;
	private @Getter @Setter int age;
}
