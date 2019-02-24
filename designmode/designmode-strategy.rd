策略模式:
定义:是一种比较简单的模式，也叫做政策模式。定义一组
算法，将每个算法都封装起来，并且使它们之间可以互换。

优点:
1.算法可以自由切换
2.避免使用多重条件判断
如果没有策略模式，我们想想看会是什么样子？一个策略家族有5个策略算法，一会要
使用A策略，一会要使用B策略，怎么设计呢？使用多重的条件语句？多重条件语句不易维
护，而且出错的概率大大增强。使用策略模式后，可以由其他模块决定采用何种策略，策略
家族对外提供的访问接口就是封装类，简化了操作，同时避免了条件语句判断。
3.扩展性良好
这甚至都不用说是它的优点，因为它太明显了。在现有的系统中增加一个策略太容易
了，只要实现接口就可以了，其他都不用修改，类似于一个可反复拆卸的插件，这大大地符
合了OCP原则。

缺点:
1.策略类数量增多
2.所有的策略类都需要对外暴露

使用场景:
1.多个类只有在算法或行为上稍有不同的场景。
2.算法需要自由切换的场景。
3.需要屏蔽算法规则的场景。

注意:
如果系统中的一个策略家族的具体策略数量超过4个，则需要考虑使用混合模式，解决
策略类膨胀和对外暴露的问题，否则日后的系统维护就会成为一个烫手山芋，谁都不想接。




