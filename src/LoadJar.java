import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class LoadJar {

    private static void test1() {
//        String filepath = "/Users/xutao/Desktop/xutao/code/IDEA/files/classes.jar";
//        File file = new File(filepath);
//
//        Set<Class<?>> classes = new LinkedHashSet<Class<?>>();//所有的Class对象
//        Map<Class<?>, Annotation[]> classAnnotationMap = new HashMap<Class<?>, Annotation[]>();//每个Class对象上的注释对象
//        Map<Class<?>, Map<Method, Annotation[]>> classMethodAnnoMap = new HashMap<Class<?>, Map<Method,Annotation[]>>();//每个Class对象中每个方法上的注释对象
//        try {
//            URL url = new URL("file:" + filepath);
//            ClassLoader loader = new URLClassLoader(new URL[]{url});//自己定义的classLoader类，把外部路径也加到load路径里，使系统去该路经load对象
//            Enumeration<JarEntry> es = jarFile.entries();
//            while (es.hasMoreElements()) {
//                JarEntry jarEntry = (JarEntry) es.nextElement();
//                String name = jarEntry.getName();
//                if(name != null && name.endsWith(".class")){//只解析了.class文件，没有解析里面的jar包
//                    //默认去系统已经定义的路径查找对象，针对外部jar包不能用
//                    //Class<?> c = Thread.currentThread().getContextClassLoader().loadClass(name.replace("/", ".").substring(0,name.length() - 6));
//                    Class<?> c = loader.loadClass(name.replace("/", ".").substring(0,name.length() - 6));//自己定义的loader路径可以找到
//                    System.out.println(c);
//                    classes.add(c);
//                    Annotation[] classAnnos = c.getDeclaredAnnotations();
//                    classAnnotationMap.put(c, classAnnos);
//                    Method[] classMethods = c.getDeclaredMethods();
//                    Map<Method, Annotation[]> methodAnnoMap = new HashMap<Method, Annotation[]>();
//                    for(int i = 0;i<classMethods.length;i++){
//                        Annotation[] a = classMethods[i].getDeclaredAnnotations();
//                        methodAnnoMap.put(classMethods[i], a);
//                    }
//                    classMethodAnnoMap.put(c, methodAnnoMap);
//                }
//            }
//            System.out.println(classes.size());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
