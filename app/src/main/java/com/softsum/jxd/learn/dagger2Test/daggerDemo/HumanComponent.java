package com.softsum.jxd.learn.dagger2Test.daggerDemo;

import dagger.Component;

@Component
public interface HumanComponent {
    void inject(Human human);
}
