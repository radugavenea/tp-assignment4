package presentationUtils;

import javax.swing.table.DefaultTableModel;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * Created by radu on 26.04.2017.
 */
public class GenericTableModel<T> extends DefaultTableModel {

    private final Class<T> type;

    public GenericTableModel(){
        this(new ArrayList<T>());
    }

    public GenericTableModel(List<T> modelData){
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        setDataVector(modelData);
    }


    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }



    public void setDataVector(List<T> modelData){
        Object[][] modelDataObjects = getModelDataObjects(modelData);
        String[] columnNames = getColumnNames();
        super.setDataVector(modelDataObjects,columnNames);
    }



    private Object[][] getModelDataObjects(List<T> modelData){
        int rowCount = modelData.size();
        int columnCount = type.getDeclaredFields().length;

        Object[][] modelDataObjects = new Object[rowCount][columnCount];

        try {
            for(int i=0; i<rowCount; i++){
                for(int j=0; j<columnCount; j++){
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(type.getDeclaredFields()[j].getName(),type);
                    Method method = propertyDescriptor.getReadMethod();
                    modelDataObjects[i][j] = method.invoke(modelData.get(i));
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return modelDataObjects;
    }



    private String[] getColumnNames(){
        int columnCount = type.getDeclaredFields().length;
        String[] columnNames = new String[columnCount];

        for(int i=0; i<columnCount; i++){
            columnNames[i] = type.getDeclaredFields()[i].getName().toString();
        }

        return columnNames;
    }


}


