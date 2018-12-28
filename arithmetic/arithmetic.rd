一.线性表
 顺序存储结构
  ArrayList
 底层采用数组结构，数组开辟一段连续的内存空间。
线性表顺序存储结构：
  存储空间容量 max
  数据类型
  data[max]
  size当前线性表的大小,数据元素的个数

 ArrayList可以指定初始容量，如果不指定就是空的数组。默认初始容量是12，最好指定初始容量，可以少创建数组，降低消耗。

add():
 public boolean add(E object) {
        Object[] a = array;
        int s = size;
        if (s == a.length) {
            //增长因子是列表个数的1/2
            Object[] newArray = new Object[s +
                    (s < (MIN_CAPACITY_INCREMENT / 2) ?
                            MIN_CAPACITY_INCREMENT : s >> 1)];
//            public static void arraycopy(Object src,int srcPos,Object dest,int destPos,int length) 量越大速度越明显
            System.arraycopy(a, 0, newArray, 0, s);
            array = a = newArray;
        }
        //存取时间复杂度 O{1}
        a[s] = object;
        size = s + 1;
        modCount++;
        return true;
    }
添加元素的时候，如果元素的个数等于当前数组的长度，则创建一个新的数组，数组的长度等于size+size>>2,增长因子是原来的一半。
如果元素的个数小于数组的长度则直接放到size对应的位置，然后线性表的长度+1
get(int i):
  public E get(int index) {
        if (index >= size) {
            throwIndexOutOfBoundsException(index, size);
        }
        return (E) array[index];
    }

get,add时间复杂度都是O{1}

add(int i)插入元素:
public void add(int index, E object) {
        Object[] a = array;
        int s = size;
        if (index > s || index < 0) {
            throwIndexOutOfBoundsException(index, s);
        }

        if (s < a.length) {
            System.arraycopy(a, index, a, index + 1, s -index);
        } else {
            // assert s == a.length;
            Object[] newArray = new Object[newCapacity(s)];
            System.arraycopy(a, 0, newArray, 0, index);
            System.arraycopy(a, index, newArray, index + 1, s - index);
            array = a = newArray;
        }
        a[index] = object;
        size = s + 1;
        modCount++;
    }
  在指定位置插入元素，如果要插入的位置大于当前元素的个数，则抛出异常。比如当前元素个数是3，插入位置是4，不符合顺序存储结构。
  如果当前元素个数小于存储容量数组的长度，则把index之后的元素都向后移动一位。然后index位置等于新元素，线性表个数+1；
  如果如果当前元素个数大于存储容量数组的长度，创建新数组，增长因子是原来的一半。然后再拷贝原数组数据到新数组，并留出index位置。
  System.arraycopy：JVM 内部固有方法，它通过手工编写汇编或其他优化方法来进行 Java 数组拷贝,因此效率比较高。



delete(int i)删除元素:
    public E remove(int index) {
        Object[] a = array;
        int s = size;
        if (index >= s) {
            throwIndexOutOfBoundsException(index, s);
        }
        @SuppressWarnings("unchecked") E result = (E) a[index];
        System.arraycopy(a, index + 1, a, index, --s - index);
        a[s] = null;  // Prevent memory leak
        size = s;
        modCount++;
        return result;
    }
顺序存储结构优点：
1.无需为表示表中元素的逻辑关系而增加额外的存储空间
2.可以快速的存取表中任一位置的元素
顺序存储结构缺点：
1.插入和删除操作需要移动大量的元素
2.当线性表的长度变化较大时，难以确定存储空间的容量
3.造成存储空间的碎片
---------------------------------------------------------------------------------------------------------------

链式存储结构
LinkedList
底层维护一个对象结点，对象中保存有数据，和上一个，下一个节点。
  private static class Entry<E> {
        E element;
        Entry<E> next;
        Entry<E> previous;

        Entry(E element, Entry<E> next, Entry<E> previous) {
            this.element = element;
            this.next = next;
            this.previous = previous;
        }
    }
linkedlist中创建了一个header结点，Entry<E> header = new Entry<E>(null, null, null);
header结点的next指向第一个元素，previours指向最后一个元素
add(E)元素
 private Entry<E> addBefore(E e, Entry<E> entry) {
        Entry<E> newEntry = new Entry<E>(e, entry, entry.previous);
        newEntry.previous.next = newEntry;
        newEntry.next.previous = newEntry;
        size++;
        modCount++;
        return newEntry;
    }
    add（E）元素就是在头结点的前面添加元素，当前元素就是头结点。
    add(i,E)就是在i位置对应的结点的前面添加添加元素，当前元素就是i对应的结点。
    新添加的元素的下一个节点就是当前元素，上一个结点就是
    当前元素的上一个结点。当前结点元素，指针区域都已经存在。再修改新添加元素的上一个元素的next等于新添加的元素，和下一个元素的previous
    等于新添加的元素即可。
 get(i)获取元素
     private Entry<E> entry(int index) {
            if (index < 0 || index >= size)
                throw new IndexOutOfBoundsException("Index: " + index +
                        ", Size: " + size);
            Entry<E> e = header;
            if (index < (size >> 1)) {
                for (int i = 0; i <= index; i++)
                    e = e.next;
            } else {
                for (int i = size; i > index; i--)
                    e = e.previous;
            }
            return e;
        }
  获取某个位置的元素，从头指针开始遍历，直到第index位置的元素，然后返回。

add(i,E):插入元素
public void add(int index, E element) {
        addBefore(element, (index == size ? header : entry(index)));
    }

remove(i)：删除元素:
 public E remove(int index) {
        return remove(entry(index));
    }

  private E remove(Entry<E> e) {
         if (e == header)
             throw new NoSuchElementException();

         E result = e.element;
         e.previous.next = e.next;
         e.next.previous = e.previous;
         e.next = e.previous = null;
         e.element = null;
         size--;
         modCount++;
         return result;
     }
删除元素就是把当前元素的上一个结点的next指向当前元素的下一个结点。
当前元素的下一个结点的previous指向当前元素的上一个结点。

插入，删除元素都需要先遍历元素，平均时间复杂度都是O{n},如果我们再不知道第i个元素的位置的情况下，
单链表和顺序存储结构都是一样的。但是如果我们知道第i个位置，然后批量插入元素的情况下，顺序存储结构每次都得移动元素。这时候
单链表的优势就体现出来了，只需要移动指针即可。

---------------------------------------------------------------------------------------------------------------

单链表和顺序存储结构的优缺点
一.存储分配方式
 顺序存储结构用一段连续的存储单元依次存储线性表的数据元素。
 单链表采用链式存储结构，用一组任意的存储单元存放线性表的元素。
二.时间性能
查找
顺序存储O[1]
链式存储O[n]
插入删除
顺序存储结构需要移动表长一半的元素，时间复杂度为O[n]
单链表在知道某位置的指针后，时间复杂度为O[1]
三.空间性能
顺序存储结构需要预分配存储空间，分大了，浪费，分小了易发生上溢。
单链表不需要分配存储空间，只要有就可以分配，元素个人数也不受限制。

ArrayList和LinkedList的区别：
ArrayList底层采用数组结构，即是顺序存储结构。因此查找速度快，时间复杂度为O[1].插入删除速度慢，需要移动元素时间复杂度为O[n]。
ArrayList需要预先分配存储空间，如果空间不够，扩容增长因子是现有元素的一半。
LinkedList底层采用链表结构，即是链式存储。因此查找速度相对慢点，时间复杂度为O[n].在知道位置的情况下，插入删除快，时间复杂度O[1]
LinkedList不需要预先分配存储空间。
---------------------------------------------------------------------------------------------------------------
栈
栈是限定仅在表尾进行插入和删除操作的线性表。
把允许插入和删除的一端称为栈顶，另一端称为栈底，不含任何数据元素的栈称为空栈。
栈又被称为后进先出(Last In First Out)的线性表，简称LIFO结构
Stack继承自Vector，所以是顺序存储结构
Vector和ArrayList一样，底层维护了一个数组，但是Vector中的方法都加了synchronized关键字，所以是线程安全的。
Vector初始容量是10，每次增长一半。
Stack的pop方法是线程安全的。push方法没有加synchronized。
队列
队列是只允许在一端进行插入操作，而在另一端进行删除操作的线性表。

-------------------------------------------------------------------------------------------------------------------------
树
树(Tree)是n(n>=0)个结点的有限集。n=0的时称为空树。在任意一棵非空树中:
(1)有且仅有一个称为根的结点;
(2)当n>1时，其余结点可分为m(m>0)个互不相交的有限集T1、T2、...Tm,其中每一个集合本身又是一棵树，并且称为根的子树。
结点拥有的子树称为结点的度(Degree)。
度为0的结点称为叶结点（Leaf）或终端结点;
度不为0的结点称为非终端结点或分支结点。
除根结点外分支结点也称内部结点。树的度是树内各结点的度的最大值。

结点的子树的根称为该结点的孩子，相应地，该结点称为孩子的双亲
同一个双亲的孩子之间互称为兄弟。
结点的祖先是从根结点到该结点所经分支上的所有结点。
以某结点为根的子树中的任一结点都称为该结点的子孙。
树中结点的最大层次称为树的深度或高度。
树的存储结构
1.双亲表示法
在每个结点中，附设一个指示器指示其双亲结点到链表中的位置。
2.孩子表示法
把每个结点的孩子结点排列起来，以单链表作存储结构，则n个结点有n个孩子链表，如果是叶子结点则此单链表为空。
然后n个头指针又组成一个线性链表，采用顺序存储结构，存放进一个一维数组中。
3.孩子兄弟表示法
任意一棵树，它的结点的第一个孩子如果存在就是唯一的，它的右兄弟如果存在也是唯一的。因此，我们设置两个指针，分别指向该结点的第一个孩子
和此结点的兄弟结点。

二叉树的性质:
1.在二叉树的第i层上至多有2^(i-1)个结点。
2.深度为k的二叉树至多有2^k-1个结点(等比数列求和An=A1*(1-q^n)/(1-q))
3.对任何一个二叉树T，如果其终端结点数为n0,度为2的结点数为n2,则n0=n2+1;
4.具有n个结点的完全二叉树的深度为[log2n]+1,[x]表示不大于x的最大整数
5.
二叉树的存储结构：
二叉树的顺序存储
顺序存储一般适用于完全二叉树
二叉树的遍历:
二叉树的遍历是指从根结点出发，按照某种次序以此访问二叉树中所有结点，使得每个结点被访问一次且仅被访问一次。
1.前序遍历
先访问根结点，再遍历左右子树，称为“先序遍历”；
若二叉树为空，则空操作返回，否则先访问根结点，然后前序遍历左子树，再前序遍历右子树。
打印，左子树，右子树
2.中序遍历
遍历左子树，之后访问根结点，然后遍历右子树，称为“中序遍历”；
若二叉树为空，则空操作返回，否则从根结点开始(注意并不是先访问根结点)，中序遍历根结点的左子树，然后是访问根结点，最后中序遍历右子树。
左子树，打印，右子树
3.后序遍历
遍历完左右子树，再访问根结点，称为“后序遍历”。
若二叉树为空，则空操作返回，否则从左到右先叶子后结点的方式遍历访问左子树，最后是访问根结点。
左子树，右子树，然后再打印
4.层序遍历
若二叉树为空，则空操作返回，否则从树的第一层，也就是根结点开始访问，从上而下逐层遍历，在同一层中，按从左到右的顺序对结点逐个访问。

线索二叉树
对二叉树以某种次序遍历使其变为线索二叉树的过程称作是线索化。
线索化的过程就是在遍历的过程中修改空指针的过程。

树转换为二叉树
1.加线。在所有兄弟结点之间加一条连线。
2.去线。在树中每个结点，只保留它与第一个孩子结点的连线，删除他与其他孩子结点之间的连线。
3.层次调整。以树的根结点为轴心，将整棵树顺时针旋转一定的角度，使之结构层次分明。注意第一个孩子是二叉树结点的左孩子，兄弟转换过来的孩子是结点的有孩子。

哈夫曼树
当用 n 个结点（都做叶子结点且都有各自的权值）试图构建一棵树时，
如果构建的这棵树的带权路径长度最小，称这棵树为“最优二叉树”，有时也叫“赫夫曼树”或者“哈夫曼树”。
在构建哈弗曼树时，要使树的带权路径长度最小，只需要遵循一个原则，那就是：权重越大的结点离树根越近。
构建哈夫曼树:
对于给定的有各自权值的 n 个结点，构建哈夫曼树有一个行之有效的办法：
在 n 个权值中选出两个最小的权值，对应的两个结点组成一个新的二叉树，且新二叉树的根结点的权值为左右孩子权值的和；
在原有的 n 个权值中删除那两个最小的权值，同时将新的权值加入到 n–2 个权值的行列中，以此类推；
重复 1 和 2 ，直到所以的结点构建成了一棵二叉树为止，这棵树就是哈夫曼树。

图:
图是由顶点的有穷非空集合和顶点之间边的集合组成，通常表示为：G(V,E),其中，G表示一个图，V是图G中顶点的集合，E是图G中边的集合。





