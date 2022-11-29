package cn.allms.leave.domain.event;

import com.codingapi.springboot.framework.event.IAsyncEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserNameChangeEvent implements IAsyncEvent {
    private String oldName;
    private String currentName;
}
