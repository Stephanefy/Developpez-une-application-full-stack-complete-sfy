package com.openclassrooms.mddapi.domains.dtos.user;

import com.openclassrooms.mddapi.domains.models.Theme;
import lombok.Data;

import java.util.Set;

@Data
public class UserSubscriptionsDTO {

    private Set<Theme> subscriptions;
}
