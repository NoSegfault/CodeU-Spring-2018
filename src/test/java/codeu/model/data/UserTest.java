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
import org.junit.Assert;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;


public class UserTest {

  @Test
  public void testCreate() {
    UUID id = UUID.randomUUID();
    String name = "test_username";
    String password = "password";
    String hashedPassword = BCrypt.hashpw(password,BCrypt.gensalt());
    Instant creation = Instant.now();

    User user = new User(id, name, hashedPassword, creation);

    Assert.assertEquals(id, user.getId());
    Assert.assertEquals(name, user.getName());
    Assert.assertEquals(hashedPassword, user.getPassword());
    Assert.assertEquals(creation, user.getCreationTime());
  }
}
