package rs.edu.raf.BankService.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import rs.edu.raf.BankService.data.enums.AccountType;
import rs.edu.raf.BankService.data.enums.UserAccountUserProfileConnectionState;

import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private UserAccountUserProfileConnectionState linkedWithUserProfile;
    private String email;
    private boolean status;
    private AccountType accountType;
    private Long availableBalance;
    private Long reservedFunds;
    private Long employeeId;
    private Long creationData;
    private Long expirationDate;
    private String currencyCode;
    private Double maintenanceFee;

    public Account(
            String accountNumber,
            String email,
            AccountType accountType,
            String currencyCode,
            Double maintenanceFee
    ){
        this.accountNumber = accountNumber;
        this.linkedWithUserProfile = UserAccountUserProfileConnectionState.NOT_ASSOCIATED;
        this.email = email;
        this.status = true;
        this.accountType = accountType;
        this.availableBalance = 0L;
        this.reservedFunds = 0L;
        this.employeeId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.creationData = new Date().getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 2);
        this.expirationDate = calendar.getTime().getTime();
        this.currencyCode = currencyCode;
        this.maintenanceFee = maintenanceFee;
    }
}
