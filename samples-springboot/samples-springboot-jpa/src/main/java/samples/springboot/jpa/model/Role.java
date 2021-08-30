package samples.springboot.jpa.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: daibin
 * @date: 2021/7/31 1:21 上午
 */
@Entity
@Table(name = "t_role")
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = 5024805027807941130L;
    @Id
    private String role;

    private String username;
}
