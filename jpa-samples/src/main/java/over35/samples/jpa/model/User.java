package over35.samples.jpa.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: daibin
 * @date: 2021/7/31 12:23 上午
 */
@Entity
@Table(name = "t_user")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -1688121726408235152L;

    @Id
    private String username;

    private Boolean enabled;

    private String password;
}
