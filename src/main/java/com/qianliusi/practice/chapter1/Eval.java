package com.qianliusi.practice.chapter1;

import javax.tools.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

/**
 * Created by qianliusi on 2017/3/31.
 */
public class Eval {
	public static void main(String[] args) throws Exception {
		new Eval().eval("System.out.println(System.getProperty(\"user.dir\"));System.out.println(\"sdfsdfsdf\");");
	}

	public class JavaSourceFromString extends SimpleJavaFileObject {
		private String code;

		public JavaSourceFromString(String name, String code) {
			//super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
			super(URI.create(name + ".java"), Kind.SOURCE);
			this.code = code;
		}

		@Override
		public CharSequence getCharContent(boolean ignoreEncodingErrors) {
			return code;
		}
	}

	class MyClassLoader extends ClassLoader {
		@Override
		protected Class<?> findClass(String name) throws ClassNotFoundException {
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			//用于诊断源代码编译错误的对象
			DiagnosticCollector diagnostics = new DiagnosticCollector();
			//内存中的源代码保存在一个从JavaFileObject继承的类中
			JavaFileObject file = new JavaSourceFromString("MyCommand", name);
			JavaCompiler.CompilationTask task = compiler.getTask(null, null, null,  Arrays.asList("-d","./target/classes/com/qianliusi/practice/chapter1"), null, Arrays.asList(file));
			//编译源程序
			boolean result = task.call();
			if(result) {
				try {
					URLClassLoader classLoader = new URLClassLoader(new URL[] { new URL("file:/" + System.getProperty("user.dir")+"/target/classes/com/qianliusi/practice/chapter1") });
					return classLoader.loadClass("com.qianliusi.practice.chapter1.MyCommand");
				} catch(MalformedURLException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
	}

	public void eval(String command) throws Exception {
		String evalClass = "public class MyCommand implements com.qianliusi.practice.chapter1.Eval.Command{" + "public void execute(){" + command + "}" + "}";
		Class clazz = new MyClassLoader().loadClass(evalClass);
		Command c = (Command) clazz.newInstance();
		c.execute();
	}

	class MyInvocationHandler implements InvocationHandler {
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			return null;
		}
	}

	public interface Command {
		void execute();
	}
}
