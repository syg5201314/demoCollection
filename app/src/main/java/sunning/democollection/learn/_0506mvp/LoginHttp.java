package sunning.democollection.learn._0506mvp;

public class LoginHttp {
    private LoginHttp() {
    }

    private static class InstanceHolder {
        private static final LoginHttp instance = new LoginHttp();
    }

    public static LoginHttp getInstance() {
        return InstanceHolder.instance;
    }

    /**
     * 模拟网络请求
     *
     * @param account
     * @param password
     * @return
     */
    public boolean httpLogin(String account, String password) {
        if (account.equals("aaa") && password.equals("aaa")) {
            return true;
        }

        return false;
    }
}