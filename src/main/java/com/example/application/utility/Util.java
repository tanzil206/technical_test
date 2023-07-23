package com.example.application.utility;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Util {

	public static <T> ArrayList<T> getArrayListFromStream(Stream<T> stream) {

		ArrayList<T> arrayList = stream.collect(Collectors.toCollection(ArrayList::new));

		return arrayList;
	}

}
