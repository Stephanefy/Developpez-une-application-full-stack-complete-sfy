package com.openclassrooms.mddapi.domain.dtos.user;

import com.openclassrooms.mddapi.domain.models.Theme;
import lombok.Data;

import java.util.Set;

@Data
public class UserSubscriptionsDTO {

    private Set<Theme> subscriptions;
}
