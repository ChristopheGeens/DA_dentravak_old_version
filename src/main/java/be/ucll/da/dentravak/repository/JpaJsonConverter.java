package be.ucll.da.dentravak.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.HashMap;

@Converter(autoApply = true)
public class JpaJsonConverter implements AttributeConverter<Object, String> {

    private HashMap<String, Class> nameToClass = new HashMap<>();

    @Override
    public String convertToDatabaseColumn(Object entityValue) {
        if( entityValue == null )
            return null;


        try {
            String className = entityValue.getClass().getSimpleName();
            nameToClass.put(className, entityValue.getClass());
            ObjectMapper mapper = new ObjectMapper();
            String result = null;
            result = className + "|||" + mapper.writeValueAsString(entityValue);
            return result;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object convertToEntityAttribute(String databaseValue) {
        if( databaseValue == null )
            return null;



        try {
            String[] splittedDatabaseValue = databaseValue.split("\\|\\|\\|");
            String className = splittedDatabaseValue[0];
            String json = splittedDatabaseValue[1];

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(json, nameToClass.get(className));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}