适配器模式:
定义:将一个类的接口变换成客户端所期待的另一种接口，从而使原本因接口不匹配而无法在一起工作的两个类能够在一起工作。
适配器模式又叫做变压器模式，也叫做包装模式（Wrapper）
适配器模式就是把一个接口或类转换成其他的接口或类
优点:
1.适配器模式可以让两个没有任何关系的类在一起运行，只要适配器这个角色能够搞定
  他们就成。
2.增加了类的透明性
想想看，我们访问的Target目标角色，但是具体的实现都委托给了源角色，而这些对高
层次模块是透明的，也是它不需要关心的。
3.提高了类的复用度
当然了，源角色在原有的系统中还是可以正常使用，而在目标角色中也可以充当新的演
员。
4.灵活性非常好
使用场景:
适配器应用的场景只要记住一点就足够了：你有动机修改一个已经投产中的接口时，适
配器模式可能是最适合你的模式。比如系统扩展了，需要使用一个已有或新建立的类，但这
个类又不符合系统的接口，怎么办？使用适配器模式，这也是我们例子中提到的。

注意:
适配器模式最好在详细设计阶段不要考虑它，它不是为了解决还处在开发阶段的问题，
而是解决正在服役的项目问题，没有一个系统分析师会在做详细设计的时候考虑使用适配器
模式，这个模式使用的主要场景是扩展应用中，就像我们上面的那个例子一样，系统扩展
了，不符合原有设计的时候才考虑通过适配器模式减少代码修改带来的风险。
再次提醒一点，项目一定要遵守依赖倒置原则和里氏替换原则，否则即使在适合使用适
配器的场合下，也会带来非常大的改造。


对象适配器和类适配器的区别是：类适配器是类间继承，对象适配器是对象的合成关
系，也可以说是类的关联关系，这是两者的根本区别。二者在实际项目中都会经常用到，由
于对象适配器是通过类间的关联关系进行耦合的，因此在设计时就可以做到比较灵活，比如
修补源角色的隐形缺陷，关联其他对象等，而类适配器就只能通过覆写源角色的方法进行扩
展，在实际项目中，对象适配器使用到场景相对较多。




