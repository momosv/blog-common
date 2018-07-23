package cn.momosv.blog.common.wrapper;

import com.google.common.base.CaseFormat;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.MapWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

import java.util.Map;


public class MyWrapperFactory implements ObjectWrapperFactory {

        @Override
        public boolean hasWrapperFor(Object object) {
            return object != null && object instanceof Map;
        }

        @Override
        public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
            return new CustomWrapper(metaObject,(Map)object);
        }
    }
 class CustomWrapper extends MapWrapper {


    public CustomWrapper(MetaObject metaObject, Map<String, Object> map) {
        super(metaObject, map);
    }


    @Override
    public String findProperty(String name, boolean useCamelCaseMapping) {
        if(useCamelCaseMapping){
            //CaseFormat是引用的 guava库,里面有转换驼峰的,免得自己重复造轮子,pom添加
            /**
             **         <dependency>
             <groupId>com.google.guava</groupId>
             <artifactId>guava</artifactId>
             <version>24.1-jre</version>
             </dependency>
             **/
            return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,name);
        }
        return name;
    }
}
