
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestNotes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Notes> noteLst = new ArrayList<>();
	    noteLst.add(new Notes(1, "note1", 11));
	    noteLst.add(new Notes(2, "note2", 22));
	    noteLst.add(new Notes(3, "note3", 33));
	    noteLst.add(new Notes(4, "note4", 44));
	    noteLst.add(new Notes(5, "note5", 55));

	    noteLst.add(new Notes(6, "note4", 66));


	    Map<String, Long> notesRecords = noteLst.stream()
	                                            .sorted(Comparator
	                                            .comparingLong(Notes::getTagId)
	                                            ) // sorting is based on TagId 55,44,33,22,11, we can also use the reversed() function to get result on descending order
	                                            .collect(Collectors.toMap
	                                            (Notes::getTagName, Notes::getTagId,
	                                            (oldValue, newValue) -> oldValue,LinkedHashMap::new)); //handling duplicate keys
	// consider old value 44 for dupilcate key
	// it keeps order
	        System.out.println("Notes : " + notesRecords);
	}

}
class Notes {
    private int id;
    private String tagName;
    private long tagId;

    public Notes(int id, String tagName, long tagId) {
        this.id = id;
        this.tagName = tagName;
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public long getTagId() {
        return tagId;
    }
}

