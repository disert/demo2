# demo2
使用retrofit rxJava dagger 搭建的一个框架的demo

# 要执行网络请求时

1.去ApiInterface中定义接口

    @GET("http://baike.baidu.com/api/openapi/BaikeLemmaCardApi?scope=103&format=json&appid=379020&bk_key=美女&bk_length=600")
    Observable<DemoJsonBean> demojson();

2.在想调用的地方

        ApiUtils.request(ApiUtils.getApiInterface().demojson(), new Subscriber<DemoJsonBean>() {
            @Override
            public void onCompleted() {
                
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(DemoJsonBean demoJsonBean) {

            }
        });
        
当然,这个结果出来还可以再优化一下

# 想对数据进行统一处理

你需要去ApiUtils中在request方法中为Observable调用一下map方法,在传入的Action中进行处理

# 想要动态注入对象

1.去AppMoudle中定义提供的方法,当然也可以在对象的构造上面添加@Inject注释,下面是提供APIInterface对象的示例:

    @Provides
    Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//解析器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
    }
    @Provides
    OkHttpClient provideOkHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();
        return client;
    }
    @Provides
    ApiInterface provideApiInterface(Retrofit retrofit){
        return retrofit.create(ApiInterface.class);
    }

2.在AppComponent中增加inject方法传入一个类的字节码(你想注释在哪个类里面就传谁的字节码)

void inject(GlobalContext context);

3.在第二步传入的字节码所代表的类中执行注释

mAppComponent = DaggerAppComponent.builder()
				.appMoudule(new AppMoudule())
				.build();
		mAppComponent.inject(this);

