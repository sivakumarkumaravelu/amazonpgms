import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class FrequentlyUsedWords {
	static List<String> retrieveMostFrequentlyUsedWords(String literatureText, String... ignoreWords)
	{
		List<String> wordsToExclude = Arrays.asList(ignoreWords);
		
		//Count the number of occurance, excluding few words.
		List<String> words = Arrays.asList(literatureText.split("[.!?'\\s+]"));
		Set<Entry<String, Integer>> set =
				words.stream()
				.filter(word -> !wordsToExclude.contains(word))
				.collect(Collectors.toMap(word -> word.toLowerCase(), 
						word -> 1, Integer::sum))
				.entrySet();
		
		//Compute the maximum of words from the set
		int max = Collections.max(
				set, 
				Comparator.comparingInt(Map.Entry::getValue)).getValue();
		
		//Compare from the set and maximum occurance and add them in list and send back.
		List<String> list =
				set.stream()
				.filter(entry -> entry.getValue() == max && !wordsToExclude.contains(entry.getKey()))
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());

		return list;
	}

	public static void main(String[] args) {
		String inputTxt = "Jack and Jill went to the market to buy bread and cheese. Cheese is Jack\'s and Jill\'s favorite food.";
		String[] ignoreWords = {"and", "he", "the", "to", "is", "Jack", "Jill"};
		List<String> result;
		result = retrieveMostFrequentlyUsedWords(inputTxt, ignoreWords);
		System.out.println(result);
	}
}