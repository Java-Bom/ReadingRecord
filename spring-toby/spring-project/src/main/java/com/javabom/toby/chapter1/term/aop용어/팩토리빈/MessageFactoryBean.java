package com.javabom.toby.chapter1.term.aop용어.팩토리빈;

import com.javabom.toby.chapter1.term.aop용어.프록시.Message;
import com.javabom.toby.chapter1.term.aop용어.프록시.MessageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

@RequiredArgsConstructor
public class MessageFactoryBean implements FactoryBean<Object> {
    private final Message target;
    private final Class<?> type;

    @Override
    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{Message.class},
                new MessageHandler(target));
    }

    @Override
    public Class<?> getObjectType() {
        return this.type;
    }
}
