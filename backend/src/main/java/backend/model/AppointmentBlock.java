package backend.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "appointment_block")
public class AppointmentBlock extends Block {
    public AppointmentBlock() {
    }

}
