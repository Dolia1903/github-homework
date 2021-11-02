package json;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.Jsoner;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JsonParser {
    private static final String PATH = "D:\\Git\\DZ13_Elementary\\src\\main\\resources\\journal_ru.json";

    public static void main(String[] args) throws IOException, JsonException {
        try (FileReader fileReader = new FileReader((PATH))) {
            JsonArray objects = Jsoner.deserializeMany(fileReader);
            Mapper mapper = new DozerBeanMapper();

            JsonArray o = (JsonArray) objects.get(0);
            List<DayJournal> collect = o.stream()
                    .map(x -> mapper.map(x, DayJournal.class)).collect(Collectors.toList());
            collect.forEach(System.out::println);
        }
    }
}
