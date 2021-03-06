/*
 * Copyright (C) 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.copybara.git.gerritapi;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.reflect.TypeToken;
import com.google.copybara.RepoException;
import com.google.copybara.ValidationException;
import com.google.copybara.profiler.Profiler;
import com.google.copybara.profiler.Profiler.ProfilerTask;
import java.util.List;

/**
 * A mini API for getting and updating Gerrit projects through the Gerrit REST API.
 */
public class GerritApi {

  private final GerritApiTransport transport;
  private final Profiler profiler;

  public GerritApi(GerritApiTransport transport, Profiler profiler) {
    this.transport = Preconditions.checkNotNull(transport);
    this.profiler = Preconditions.checkNotNull(profiler);
  }

  public List<ChangeInfo> getChanges(ChangesQuery query)
      throws RepoException, ValidationException {
    try (ProfilerTask ignore = profiler.start("gerrit_get_changes")) {
      List<ChangeInfo> result = transport.get("/changes/?" + query.asUrlParams(),
                                              new TypeToken<List<ChangeInfo>>() {}.getType());
      return ImmutableList.copyOf(result);
    }
  }

  public ChangeInfo abandonChange(String changeId, AbandonInput abandonInput)
      throws RepoException, ValidationException {
    try (ProfilerTask ignore = profiler.start("gerrit_abandon_change")) {
      return transport.post("/changes/" + changeId + "/abandon", abandonInput, ChangeInfo.class);
    }
  }

  public ChangeInfo restoreChange(String changeId, RestoreInput restoreInput)
      throws RepoException, ValidationException {
    try (ProfilerTask ignore = profiler.start("gerrit_restore_change")) {
      return transport.post("/changes/" + changeId + "/restore", restoreInput, ChangeInfo.class);
    }
  }
}
