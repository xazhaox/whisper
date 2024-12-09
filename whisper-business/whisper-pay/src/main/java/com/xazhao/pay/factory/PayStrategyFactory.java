package com.xazhao.pay.factory;

import cn.hutool.core.util.ObjectUtil;
import com.xazhao.core.context.SpringContextHolder;
import com.xazhao.pay.enums.PayStrategyEnum;
import com.xazhao.pay.exception.PayErrorCode;
import com.xazhao.pay.exception.PayException;
import com.xazhao.pay.strategy.PayStrategy;
import com.xazhao.pay.strategy.impl.AlipayServiceImpl;
import com.xazhao.pay.strategy.impl.UnionPayServiceImpl;
import com.xazhao.pay.strategy.impl.WeChatServiceImpl;
import jakarta.annotation.Resource;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 支付策略工厂，通过工厂类获取具体的策略实现（具体的策略类）
 *
 * @Description Created on 2024/08/28.
 * @Author Zhao.An
 */

@Component
public class PayStrategyFactory implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * 私有化构造函数，单例
     */
    private PayStrategyFactory() {

    }

    /**
     * 弃用<br/>
     * 第二种方式获取所有的支付策略：注册一个静态的Map在初始项目时将各种支付平台都注册到这个Map中，通过支付类型获取具体的策略实现
     * 需要接入新的支付平台时，只需要在这个Map中将新接入的支付平台（新的策略实现类）注册进来即可
     * Map的key就是前端传回来的支付平台的标识，如：支付宝就是Alipay（前后端约定Map的key）
     * 缺点：每接入一个支付平台就需要在这个Map中手动的注册一个策略的实现，每次都需要改动这个工厂方法
     */
    private static Map<String, PayStrategy> registerStrategyMap = new ConcurrentHashMap<>(16);

    static {
        registerStrategyMap.put("Alipay", new AlipayServiceImpl());
        registerStrategyMap.put("WeChat", new WeChatServiceImpl());
    }

    /**
     * 弃用<br/>
     * 第三种方式获取所有的支付策略：定义一个策略的枚举类{@link PayStrategyEnum}
     * 将所有的支付平台（具体的策略实现）定义到这个枚举类中，在项目初始化的时候将所有的支付策略都注册到这个Map中
     * 缺点：每次接入一个新的支付平台就需要到这个策略枚举中新增一个枚举类型，每次接入都需要改动这个枚举类
     */
    private static Map<String, PayStrategy> enumStrategyMap = new ConcurrentHashMap<>(16);

    /**
     * <h3>在Spring所有的容器加载完成之后执行该方法</h3>
     * 该方法需要在类上实现{@link ApplicationListener<ContextRefreshedEvent>}
     * 注意：这种方法在Map中注册所有的策略实现不能直接在static代码块中注册，因为static代码块在类加载时被执行，此时Spring的容器并没有加载
     * 若是在static代码块中注册Map会造成异常，获取不到Spring的容器
     * <p/>数据来源 {@link PayStrategyFactory#enumStrategyMap}
     *
     * @param event the event to respond to
     * @see PayStrategyFactory#enumStrategyMap
     */
    @Override
    public void onApplicationEvent(@NotNull ContextRefreshedEvent event) {
        for (PayStrategyEnum e : PayStrategyEnum.values()) {
            // 通过Bean的名称获取Bean注册到Map中，Bean的名称如：@Component("WeChat")，名称为WeChat与枚举中的className保持一致
            PayStrategy payStrategy = SpringContextHolder.getBean(e.getClassName());
            enumStrategyMap.put(e.getCode(), payStrategy);
        }
    }

    /**
     * <h3>当前正在使用的方式，通过Spring容器的方式注入，获取所有的支付策略</h3>
     * 使用@Resource注解作用在Map上时，Spring会将相同的类型的对象注入到Map中，也就是只要实现了PayStrategy接口的类都会被注入到Map中
     * 并且将对象的名字（类名）作为Map的key，对象实例作为Value，若是自定义了Bean的别名则Map的key则为Bean的自定义别名
     * 例如：在声明Bean时用注解修改默认Bean名称，{@link @Component("Alipay")}或{@link @Service("Alipay")}此时Map的key则为自定义名称Alipay
     * <p>
     * 优点：接入新的支付平台时只需要注册一个支付策略类（PayStrategy）的具体策略实现类<p>
     * 例如：{@link UnionPayServiceImpl}在这个策略实现类中使用{@link @Component("UnionPay")}声明一个Bean
     * 并且将Bean的名称名称修改为自定义名称UnionPay，并且不需要手动在Map中注册这个策略类，Spring会将这个类注入到Map中
     * <h3>注意：Map的key必须是{@link String}类型的</h3>
     */
    @Resource
    private Map<String, PayStrategy> payStrategyMap = new ConcurrentHashMap<>(16);

    /**
     * <h3>获取支付策略（支付平台）获取到的实际上是PayStrategy的实现，也就是具体的策略实现类</h3>
     * <h3>数据来源 {@link PayStrategyFactory#payStrategyMap}</h3>
     *
     * @param payType 支付平台
     * @return 支付策略类
     * @see PayStrategyFactory#payStrategyMap
     */
    public PayStrategy getPayStrategy(String payType) {

        if (!payStrategyMap.containsKey(payType)) {
            return null;
        }

        // 根据@Component注解中别名获取具体的策略实现（策略类）如：@Component("Alipay")或@Service("Alipay")为支付宝
        PayStrategy payStrategy = payStrategyMap.get(payType);

        if (!ObjectUtil.isNotEmpty(payStrategy)) {
            // 未查询到具体策略
            throw new PayException("获取指定支付平台出现异常，请检查具体实现！", PayErrorCode.GET_PAY_POLICY_ERROR);
        }

        // 这返回的实际上是PayStrategy的实现（具体的策略实现类）
        return payStrategy;
    }
}
