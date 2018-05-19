// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package codeu.model.data;

import java.time.Instant;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;



/** Class representing a map from username to conversation id. */
public class UserConversationMap {
  private final UUID userID;
  private final UUID conversationID;

  /**
   * Constructs a new User.
   *
   * @param userID the ID of a User
   * @param conversationID the ID of a Conversation
   */
  public UserConversationMap(UUID userID, UUID conversationID) {
    this.userID = userID;
    this.conversationID = conversationID;
  }

  /** Returns the ID of this User. */
  public UUID getUserID() {
    return userID;
  }

  public UUID getConversationID() {
    return conversationID;
  }

}
