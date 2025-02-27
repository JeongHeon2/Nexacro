package kr.co.seoulit.logistics.sys.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.WordUtils;
import org.springframework.stereotype.Component;

import com.nexacro17.xapi.data.DataSet;
import com.nexacro17.xapi.data.DataSetList;
import com.nexacro17.xapi.data.DataTypes;
import com.nexacro17.xapi.data.PlatformData;

import kr.co.seoulit.logistics.sys.annotation.ColumnName;
import kr.co.seoulit.logistics.sys.annotation.Dataset;
import kr.co.seoulit.logistics.sys.annotation.RemoveColumn;

@Component
public class DatasetBeanMapper {

	//여러행의 정보를 받아올때
	//name=gds_estimateDetail, alias=gds_estimateDetail, columnCount=11, rowCount=2, charset=null, isStoreDataChanges=true
    public <T> List<T> datasetToBeans(PlatformData reqData, Class<T> classType) throws Exception {
        String datasetName = getDataSetName(classType);
        // datasetName은 TO클래스 위에 @Dataset(name="")으로 지정된 이름
        DataSet dataset = reqData.getDataSet(datasetName);
        // 그 이름으로 저장된 view단에서 날라온 dataset 찾는다 name=gds_company

        if (dataset == null) {
            System.out.println("Dataset " + datasetName + " not found in PlatformData.");
            return new ArrayList<>(); // 데이터셋이 없으면 빈 리스트 반환
        }

        List<T> beanList = new ArrayList<T>();
        T bean = null;
        int rowCount = dataset.getRowCount(); // xml로 날라온 rowCount를 구한다. 즉 한개의 행
        System.out.println("rowCount : " + rowCount);

        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) { // 데이터의 행만큼 돌면서 아래 진행 2개면 0,1
            try {
                bean = getBean(dataset, classType, rowIndex); // 데이터를 set 다해준 bean을 리턴 받았다.
                beanList.add(bean); // 그리고 여러개의 bean을 list에 넣어주고 return 해준다.
                System.out.println("after getBean");
            } catch (Exception e) {
                System.out.println("Error processing row index " + rowIndex + ": " + e.getMessage());
            }
        }

        rowCount = dataset.getRemovedRowCount(); // 삭제
        System.out.println("getRemovedRowCount: " + rowCount);

        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            try {
                bean = getDeletedBean(dataset, classType, rowIndex); // setStatus를 delete로 설정해준다,
                beanList.add(bean);
            } catch (Exception e) {
                System.out.println("Error processing deleted row index " + rowIndex + ": " + e.getMessage());
            }
        }
        return beanList;
    }


    //view단에서 받아온 dataSet인 한개일때  bean 객체 넣을때
    //name=gds_estimate, alias=gds_estimate, columnCount=8, rowCount=1, charset=null, isStoreDataChanges=tru
    public <T> T datasetToBean(PlatformData reqData, Class<T> classType) throws Exception {
        T bean = null;
        String datasetName = getDataSetName(classType);  //TO클래스 위에 선언된 @DataSet name을 가져온다
        System.out.println("datasetName:"+datasetName); //gds_company
        DataSet dataset = reqData.getDataSet(datasetName);  // 그 이름에 해당하는 view단에서 날아온 dataset을 찾는다.
        
        
        System.out.println("dataset.getRemovedRowCount()"+dataset.getRemovedRowCount()); 
        if(dataset.getRemovedRowCount() == 0)
            bean = getBean(dataset, classType, 0); // view단에서 받아온 값들을 set메서드로 넣어준 후  bean객체를 리턴
        else
            bean = getDeletedBean(dataset, classType, 0);
        return bean;
    }
   
    //list에 담긴 bean객체를 dataset으로 처리
    public <T> void beansToDataset(PlatformData resData, List<T> beanList, Class<T> classType) throws Exception {
        Map<String, String> nameMap = new HashMap<String, String>(); //칼럼이름을 담고 칼럼value를 구할때 필요

        DataSetList datasetList = resData.getDataSetList();  // 여러 값이 담길때는 DataSetList 사용 한개는 dataset
        String datasetName = getDataSetName(classType); //TO위에 있는 @Dataset(name = "gds_estimate")의 name value값을 얻어옴.
        DataSet dataset = new DataSet(datasetName); //DataSet에 gds_estimate 이름으로 변수 등록 view단에서  gds_estimate의 변수 이름으로 받는다.
        datasetList.add(dataset); //resData에 추가.
        // datasetList에 추가를 해주는 이유는 DatasetList는 resData와 연결 되어 있기 때문이다.
        Field[] fields = classType.getDeclaredFields();  // TO에 선언된 맴버변수들을 가져온다.
        for(Field field : fields)
            setColumnName(dataset, nameMap, field);  // dataset Column에 할당 작업
        for(T bean : beanList)
            setColumnValue(dataset, nameMap, bean); // dataset에 할당 작업
    }


    //데이터를 담은 BEAN객체를 Dataset으로 변환.
    public <T> void beanToDataset(PlatformData resData, T bean, Class<T> classType) throws Exception {
        Map<String, String> nameMap = new HashMap<String, String>();
        DataSetList datasetList = resData.getDataSetList(); //응답하려는 datasetList의 객체를 구해옴

        String datasetName = getDataSetName(classType);   //각각의 TO에 선언된  @Dataset(name="gds_~")의 name 값을 가져온다.
        DataSet dataset = new DataSet(datasetName); // 응답해줄 이름으로  DataSet객체 생성 
        //즉  @Dataset(name="gds_abc") 일경우 view단에서 gds_abc로 선언된 변수에 값이 들어감!!
        datasetList.add(dataset);

        if(bean != null) { // Bean객체의 get메서드를 이용하여 dataset에 값을 셋팅해준다.
        	Field[] fields = classType.getDeclaredFields(); // bean에 선언된 filed명들을 구해온다.
            for(Field field : fields)// filed의 갯수만큼 셋팅
                setColumnName(dataset, nameMap, field); // view단에 응답할 dataset에 컬럼이름 등록을 한다. nameMap에 칼럼이름을 넣는것은 아래에서 value값을 넣기위해인듯.
                setColumnValue(dataset, nameMap, bean);  // view단에 응답할 dataset의 값을 등록.
        }
    }

    public void mapToDataset(PlatformData resData, List<Map<String, Object>> mapList, String datasetName) throws Exception {
        DataSetList datasetList = resData.getDataSetList();
        DataSet dataset = new DataSet(datasetName);
        datasetList.add(dataset);

        for(String key : mapList.get(0).keySet()) {
            String columnName = key.toUpperCase();
            dataset.addColumn(columnName, DataTypes.STRING, 256);
        }

        int rowIndex = 0;
        for(Map<String, Object> map : mapList) {
            rowIndex = dataset.newRow();
            for(String key : map.keySet()) {
                Object columnValue = map.get(key);
                dataset.set(rowIndex, key.toUpperCase(), columnValue);
            }
        }
    }

    public List<Map<String, Object>> datasetToMap(PlatformData reqData, String datasetName) throws Exception {
        List<Map<String, Object>> mapList = new ArrayList<>();

        DataSet dataset = reqData.getDataSet(datasetName);
        int rowCount = dataset.getRowCount();
        for(int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            Map<String, Object> map = new HashMap<>();
            map.put("status", dataset.getRowTypeName(rowIndex));

            for(int colIndex = 0; colIndex < dataset.getColumnCount(); colIndex++) {
                String key = dataset.getColumn(colIndex).getName();
                Object value = dataset.getObject(rowIndex, key);
                map.put(formattingToCamel(key), value);
            }
            mapList.add(map);
        }

        rowCount = dataset.getRemovedRowCount();
        for(int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            Map<String, Object> map = new HashMap<>();
            map.put("status", dataset.getRowTypeName(rowIndex));

            for(int colIndex = 0; colIndex < dataset.getColumnCount(); colIndex++) {
                String key = dataset.getColumn(colIndex).getName();
                Object value = dataset.getObject(rowIndex, key);
                map.put(formattingToCamel(key), value);
            }
            mapList.add(map);
        }
        
        return mapList;
    }

    private <T> String getDataSetName(Class<T> classType) {
        if(classType.isAnnotationPresent(Dataset.class)) // @Dataset 어노테이션이 있는 확인.
            return classType.getAnnotation(Dataset.class).name(); // 있을경우 @Dataset에 입력된 name의 value값을가져온다.
            //지정한 어노테이션을 구한다
        else
            return "ds" + classType.getName().replace("Bean", "");
    }
//https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=javaking75&logNo=220727816394
    //set 메서드만 들어오는중!
    private String getColumnName(Method method) { // TO의 set으로 시작하는 메서드 객체가 들어온다.
        String columnName = null;
        Annotation[] annotations = method.getAnnotations();  // 해당 메서드에 선언된 모든 어노테이션을 구한다.
        for (Annotation annotation : annotations) {
            if (annotation instanceof ColumnName) { //어노테이션 @ColumnName 찾는다 instanceof
                String annotaionName = ((ColumnName) annotation).name(); 
                System.out.println("getColumnName 메서드 ColumnName:"+annotaionName);
                columnName = annotaionName;
            }
        }
        //method위에 선언된 어노테이션이 없을경우 실행
        if (annotations.length == 0)
            columnName = formattingToSnake(method.getName());
        return columnName;
    }

    //view단으로 가져가기위해 Dataset에 셋팅작업.
    private void setColumnName(DataSet dataset, Map<String, String> nameMap, Field field) {
        ColumnName column = field.getAnnotation(ColumnName.class);
        RemoveColumn remove = field.getAnnotation(RemoveColumn.class);

        String columnName;
        if (column != null) {
            columnName = column.name();
        } else if (remove == null) {
            columnName = formattingToSnake(field.getName());
        } else {
            return; // 제거할 컬럼이면 처리하지 않음
        }

        // 중복 여부 확인을 효율적으로 개선
        if (!isColumnExists(dataset, columnName)) {
            dataset.addColumn(columnName, getDataType(field)); // 데이터셋에 컬럼 추가
            nameMap.put(columnName, field.getName()); // 맵에 컬럼 이름과 필드 이름 매핑
        } else {
            System.out.println("컬럼 " + columnName + "이(가) 이미 데이터셋에 존재합니다.");
        }
    }

    // 데이터셋에 특정 컬럼이 존재하는지 확인하는 헬퍼 메서드
    private boolean isColumnExists(DataSet dataset, String columnName) {
        for (int i = 0; i < dataset.getColumnCount(); i++) {
            if (dataset.getColumn(i).getName().equals(columnName)) {
                return true; // 컬럼이 이미 존재함
            }
        }
        return false; // 컬럼이 존재하지 않음
    }



    ///https://goodgid.github.io/Java-Reflection-Field-Value/
    private <T> void setColumnValue(DataSet dataset, Map<String, String> nameMap, T bean) throws Exception {
        int rowIndex = dataset.newRow();// 새로운 행 생성
       
    
        for(String columnName : nameMap.keySet()) { // view단에 쓰일 이름    ex) POST_API setColumnName에서 put을 해줬음
            String fieldName = nameMap.get(columnName); // TO에 선언된 filed명을 가져옴
            Field value = bean.getClass().getDeclaredField(fieldName.trim()); //받아온 TO객체의 fieldName과 일치하는 필드를 가져옴
            value.setAccessible(true);								 	// Private 로 되어있는 객체에 접근하기 위해서 사용
           
            dataset.set(rowIndex, columnName, value.get(bean));			// view단으로 돌아가는 dataset에 셋팅 (row순서, dataSet변수안의 칼럼이름 , bean의 각각의 맴버변수 value값
            //value.get(bean): 변수명이 bean이라는 객체의 value에 해당하는 필드객체의 데이터 
          
        }
    }

    //index=0 inserted (null, "PTN-01", "2022-01-02", null, "황민상", "2022-01-16", "EMP-01", null)
    private <T> T getBean(DataSet dataset, Class<T> classType, int rowIndex) throws Exception {
        T bean = classType.newInstance(); //동적으로 객체생성
        Method[] methods = classType.getDeclaredMethods(); // To객체에 선언된 메서드를 가져온다.
        Method statusMethod = classType.getMethod("setStatus", String.class); // baseTo에게 상속받은 상태 메서드를 찾아옴 <메서드 이름 , 매개변수의 자료형>
        String rowType = null;
        System.out.println("**상태에 따른 insert,update 설정 메소드 시작**");
        System.out.println("### 체크 ###" + dataset.getRowTypeName(rowIndex));
        switch(dataset.getRowTypeName(rowIndex)){ //index=0 normal ,updated  rowIndex 각 행의 데이터
        	case "inserted" :
        		rowType = "insert";
        		break;
        	case "updated" :
        		rowType = "update";
        		break;
        	case "removed" :
        		rowType = "delete";
        		break;
        }
        statusMethod.invoke(bean, rowType); // setStatus 메서드 호출   <1번째는 메서드가 있는 클래스의 참조값, 인자로 날릴값. 
        
        System.out.println("**getBean 메소드 시작**");
        
        
        for(Method method : methods) { //TO에 선언된 메서드를 다 가져온다
            if(method.getName().startsWith("set")) {  // set*으로 시작하는 메서드만 찾고
                String columnName = getColumnName(method);  // ESTIMATE_DETAIL_NO변환 완료 
             
                if(columnName != null) {
                    Object columnValue = dataset.getObject(rowIndex, columnName); //각 행의 컬럼이름을넣어 값을 구해온다
                  
                    if(columnValue != null)
                        method.invoke(bean, columnValue);  // TO객체의 Set메서드에 데이터 값을 셋팅해준다
                }
            }
        }
        
        return bean; // view단에서 받아온 데이터를 각각의 set메서드를 이용하여 값을 넣어주고 그 bean객체를 return 해준다.
    }
    
    
    private <T> T getDeletedBean(DataSet dataset, Class<T> classType, int rowIndex) throws Exception {
        T bean = classType.newInstance(); //동적으로 인스턴스 생성 
        Method[] methods = classType.getDeclaredMethods(); // 선언된 메서드집합을 배열로 리턴 
        Method statusMethod = classType.getMethod("setStatus", String.class); //상태관리 메서드를 찾아온다.
        statusMethod.invoke(bean, "delete"); // delete로 set해준다.
        for (Method method : methods) {
            if (method.getName().startsWith("set")) {
                String columnName = getColumnName(method); //컬럼이름을 구해온다.
                if (columnName != null) {
                    Object columnValue = dataset.getRemovedData(rowIndex, columnName); //데이터 삭제시 저장된 원본 데이터를 반환한다.
                   
                    if (columnValue != null)
                        method.invoke(bean, columnValue); // A o1=new A();   o1.method(set~~~) (columnVlaue);  이런식으로 이해하면 빠름.
                }
            }
        }
        return bean;
    }
    //타입 설정 VIEW단에서 받기위해
    private int getDataType(Field field) {
        Class<?> returnType = field.getType(); //맴버변수의 타입을 구해온다.
        if(returnType == Date.class)//날짜타입
            return DataTypes.DATE;
        else if(returnType == String.class) //문자타입
            return DataTypes.STRING;
        else if(returnType == int.class || returnType == boolean.class)
            return DataTypes.INT;
        else if(returnType == BigDecimal.class)
            return DataTypes.BIG_DECIMAL;
        else if(returnType == double.class )
            return DataTypes.DOUBLE;
        else if(returnType == byte[].class)
            return DataTypes.BLOB;
        else
            return DataTypes.NULL;
    }

  //https://hamait.tistory.com/342
    private String formattingToSnake(String name) {  //각각의 메서드 이름을 가져온다.
    	
        String regex = "([a-z])([A-Z])"; //a-z까지 다 포함
        String replacement = "$1_$2";   //소문자대문자 --> 소문자_대문자

        //Dataset-->bean
        if(name.startsWith("set") || name.startsWith("get")) {
            name = name.substring(3);
            //CompanyZipCode
        }
           
             //bean-->dataset 
            // aA -> a_A
            name = name.replaceAll(regex, replacement);
          //Company_Zip_Code
    
        
        // return A_A
   
        return name.toUpperCase(); //COMPANY_ZIP_CODE

    }

    private String formattingToCamel(String name) {

        if(name.startsWith("set") || name.startsWith("get"))
            name = name.substring(3);
        String camel = WordUtils.capitalizeFully(name, new char[]{'_'}).replaceAll("_", "");
        return camel.substring(0, 1).toLowerCase() + camel.substring(1);
    }

}