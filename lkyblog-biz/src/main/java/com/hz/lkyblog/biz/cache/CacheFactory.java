package com.hz.lkyblog.biz.cache;
import lombok.extern.slf4j.Slf4j;
/**
 * \* Created with IntelliJ IDEA.
 * \* User: 吴江南
 * \* Date: 2018/7/13
 * \* Time: 11:26
 * \* Description:将需要添加缓存的定义在这里
 * \
 */
@Slf4j
public abstract class CacheFactory {


    /**
     * @CreateCache 的字段含义
    area	“default”	如果需要连接多个缓存系统，可在配置多个cache area，这个属性指定要使用的那个area的name
    name	未定义	指定缓存的名称，不是必须的，如果没有指定，会使用类名+方法名。name会被用于远程缓存的key前缀。另外在统计中，一个简短有意义的名字会提高可读性。如果两个@CreateCache的name和area相同，它们会指向同一个Cache实例
    expire	未定义	该Cache实例的默认超时时间定义，注解上没有定义的时候会使用全局配置，如果此时全局配置也没有定义，则取无穷大
    timeUnit	TimeUnit.SECONDS	指定expire的单位
    cacheType	CacheType.REMOTE	缓存的类型，包括CacheType.REMOTE、CacheType.LOCAL、CacheType.BOTH。如果定义为BOTH，会使用LOCAL和REMOTE组合成两级缓存
    localLimit	未定义	如果cacheType为CacheType.LOCAL或CacheType.BOTH，这个参数指定本地缓存的最大元素数量，以控制内存占用。注解上没有定义的时候会使用全局配置，如果此时全局配置也没有定义，则取100
    serialPolicy	未定义	如果cacheType为CacheType.REMOTE或CacheType.BOTH，指定远程缓存的序列化方式。JetCache内置的可选值为SerialPolicy.JAVA和SerialPolicy.KRYO。注解上没有定义的时候会使用全局配置，如果此时全局配置也没有定义，则取SerialPolicy.JAVA
    keyConvertor	未定义	指定KEY的转换方式，用于将复杂的KEY类型转换为缓存实现可以接受的类型，JetCache内置的可选值为KeyConvertor.FASTJSON和KeyConvertor.NONE。NONE表示不转换，FASTJSON通过fastjson将复杂对象KEY转换成String。如果注解上没有定义，则使用全局配置。
     */


}
