package sunning.democollection.learn._0421;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.agera.Function;
import com.google.android.agera.Merger;
import com.google.android.agera.Observable;
import com.google.android.agera.Repositories;
import com.google.android.agera.Repository;
import com.google.android.agera.Result;
import com.google.android.agera.Supplier;
import com.google.android.agera.Updatable;

import sunning.democollection.BaseActivity;

/**
 * Created by sunning on 16/4/22.
 */
public class ProgramerActivity extends BaseActivity {


    private Supplier<String> supplier = new Supplier() {
        @NonNull
        @Override
        public Object get() {
            return "update!!";
        }
    };

    Observable observable = new Observable() {
        @Override
        public void addUpdatable(@NonNull Updatable updatable) {
            updatable.update();
        }

        @Override
        public void removeUpdatable(@NonNull Updatable updatable) {

        }
    };

    Repository<String> repository = Repositories.repositoryWithInitialValue("123")
            .observe(new Observable() {
                @Override
                public void addUpdatable(@NonNull Updatable updatable) {
                    updatable.update();
                }

                @Override
                public void removeUpdatable(@NonNull Updatable updatable) {

                }
            })
            .onUpdatesPerLoop()
            .thenGetFrom(supplier)
            .compile();

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
//        repository.addUpdatable(new Updatable() {
//            @Override
//            public void update() {
//
//            }
//        });


        repository = Repositories.repositoryWithInitialValue("default")
                .observe()
                .onUpdatesPerLoop()
                .getFrom(strSupplier)
                .transform(transform)
                .thenMergeIn(integerSupplier,merger)
                .compile();

        repository.addUpdatable(updatable);

    }

    private Supplier<String> strSupplier = new Supplier<String>() {
        @NonNull
        @Override
        public String get() {
            return "value";
        }
    };

    private Function<String,String> transform = new Function<String, String>() {
        @NonNull
        @Override
        public String apply(@NonNull String input) {
            return "new " + input;
        }
    };

    private Supplier<Integer> integerSupplier = new Supplier<Integer>() {
        @NonNull
        @Override
        public Integer get() {
            return 100;
        }
    };

    private Merger<String,Integer,String> merger = new Merger<String, Integer, String>() {
        @NonNull
        @Override
        public String merge(@NonNull String s, @NonNull Integer integer) {

            return s + "plus " + String.valueOf(integer);
        }
    };

    private Updatable updatable = new Updatable() {
        @Override
        public void update() {
            Log.d("TAG", repository.get());
            Result.<Integer>absent();
        }
    };


}
