/*
 * Copyright 2011,2012 Metamarkets Group Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.metamx.common.guava;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.junit.Test;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;

/**
 */
public class FilteredSequenceTest
{
  @Test
  public void testSanity() throws Exception
  {
    Predicate<Integer> pred = new Predicate<Integer>()
    {
      @Override
      public boolean apply(@Nullable Integer input)
      {
        return input % 3 == 0;
      }
    };

    for (int i = 0; i < 25; ++i) {
      List<Integer> vals = Lists.newArrayList();
      for (int j = 0; j < i; ++j) {
        vals.add(j);
      }

      SequenceTestHelper.testAll(
          String.format("Run %,d: ", i),
          new FilteredSequence<Integer>(Sequences.simple(vals), pred),
          Lists.newArrayList(Iterables.filter(vals, pred))
      );
    }
  }
}
