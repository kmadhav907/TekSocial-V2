package com.teksocial.application.repositories;

import com.teksocial.application.models.FriendsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendsRequest extends JpaRepository< FriendsModel, Long> {
    Optional<FriendsModel> findByFriendRequestSentByIdAndFriendRequestToId(Long friendSentById, Long friendToId);
    List<FriendsModel> findByFriendRequestToIdAndStatus(Long friendToId, String status);

}
