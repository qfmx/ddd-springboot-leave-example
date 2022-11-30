package cn.allms.leave.ui.core.jwt;

import com.codingapi.springboot.framework.exception.LocaleMessageException;
import com.codingapi.springboot.framework.serializable.JsonSerializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Token implements JsonSerializable {

    private String username;

    private long userId;

    private long expireTime;

    private long remindTime;

    private int expireValue;

    private String token;
    public Token(long userId,String username, int expireValue, int remindValue){
        this.userId = userId;
        this.username = username;
        this.expireValue = expireValue;
        this.expireTime = System.currentTimeMillis()+expireValue;
        this.remindTime = System.currentTimeMillis()+remindValue;
    }

    public Token() {
    }

    public boolean isExpire(){
        return expireTime <= System.currentTimeMillis();
    }

    public boolean canRestToken() {
        return !isExpire() && remindTime <= System.currentTimeMillis();
    }

    public void verify() throws LocaleMessageException {
        if(isExpire()){
            throw new LocaleMessageException("token.expired");
        }
    }


}
