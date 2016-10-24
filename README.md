#DemoCollection 使用手册
***
###1.如果现有分类包含了所属类别，直接在现有分类下创建对应 package。否则创建分类 package 。
###2 在当前 package 下创建 Activity 并继承 FrameActivity 实现getSecondLevel方法，返回 String[]，该数组定义到 res/value/arrays 中，路径定义到去除 package 的相对路径。(例如)
<li><p>二级 Activity绝对路径为</p></li>

    sunning.democollection.custom.view.tableviewexample.MainActivity

<li><p>配置二级路径为</p></li>

	.view.tableviewexample.MainActivity

#3.在 AndroidManiFest 中注册一级分类（例）
        <activity
            android:name=".font.FontActivity" //类名
            android:label="@string/font">//列表中显示的 label
            <intent-filter>//intent的 filter
                <action android:name="android.intent.action.MAIN" />
                <category android:name="com.demo.example" />
            </intent-filter>
        </activity>
#4 AndroidManiFest 中注册二级分类（例）
        <activity
            android:label="SortableTableView可以排序和定制的TableView"//显示到 ListView中的名字
            android:name=".view.tableviewexample.MainActivity">//Activity 路径
            <category android:name="com.demo.view"/>//配置category
        </activity>