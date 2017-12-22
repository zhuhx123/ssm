log4j的使用：
	log4j配置中两个重要的对像，一个是logger，一个是appender
	【logger】：
		用来记录日志的对像，如： log.error(errorMsg);
		在log4j配置文件格式如下：
		log4j.logger.${loggerName}=[level],[appender...]
		${loggerName}可以是包名，如设为：com.test.service
		则在JAVA中可这样获取：
		Logger log = Logger.getLogger("com.test.service");
		
		log4j.additivity.java.sql.Connection=false 表示不用父logger的appender
	【appender】：
		日志内容记录器，表示将内容记录（输出）到哪里。 它是提供给logger用的，一个logger可以指定使用多个appender
		Log4j提供的appender有以下几种：
			org.apache.log4j.ConsoleAppender（控制台），
			org.apache.log4j.FileAppender（文件），
			org.apache.log4j.DailyRollingFileAppender（每天产生一个日志文件），
			org.apache.log4j.RollingFileAppender（文件大小到达指定尺寸的时候产生一个新的文件），
			org.apache.log4j.WriterAppender（将日志信息以流格式发送到任意指定的地方）
			
		#设置输出样式  
		log4j.appender.appender1.layout=org.apache.log4j.PatternLayout  
		#自定义样式  
		# %r 时间 0  
		# %t 方法名 main  
		# %p 优先级 DEBUG/INFO/ERROR  
		# %c 所属类的全名(包括包名)  
		# %l 发生的位置，在某个类的某行  
		# %m 输出代码中指定的讯息，如log(message)中的message  
		# %n 输出一个换行  