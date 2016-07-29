package sunning.democollection.learn._0506mvp.base;

public interface BaseView<T> {
/**     
  * 使用fragment作为view时，将activity中的presenter传递给fragment     
  * @param presenter     
  */    
void setPresenter(T presenter);

}