package json;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

public class DayJournal implements Jsonable {
    private List<String> events;
    private boolean squirrel;

    public DayJournal() {
    }

    public List<String> getEvents() {
        return events;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }

    public boolean isSquirrel() {
        return squirrel;
    }

    public void setSquirrel(boolean squirrel) {
        this.squirrel = squirrel;
    }

    @Override
    public String toJson() {
        final StringWriter writable = new StringWriter();
        try {
            this.toJson(writable);
        } catch (final IOException ignored) {
        }
        return writable.toString();
    }

    @Override
    public void toJson(Writer writer) throws IOException {
        final JsonObject json = new JsonObject();
        json.put("events", this.getEvents());
        json.put("squirrel", this.isSquirrel());
        json.toJson(writer);
    }

    @Override
    public String toString() {
        return "{" +
                "\"events\":" + events +
                ", \"squirrel\":" + squirrel +
                '}';
    }
}
