/*
 * Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */

package software.amazon.ai.training;

import software.amazon.ai.ndarray.NDArray;

public interface ParameterServer extends AutoCloseable {

    void init(int key, NDArray[] value);

    /**
     * Push values into key in Parameter Server.
     *
     * @param key key to update
     * @param values values corresponding to key, values in array will be summed when update key
     * @param priority The priority of the push operation. Higher priority push operations are
     *     likely to be executed before other push actions
     */
    void push(int key, NDArray[] values, int priority);

    /**
     * Pull value of key from Parameter Server to NDArrays.
     *
     * @param key key to pull
     * @param values NDArrays to store the value corresponding to key, value will be copied to the
     *     devices of the NDArrays
     * @param priority The priority of the push operation. Higher priority push operations are
     *     likely to be executed before other push actions
     */
    void pull(int key, NDArray[] values, int priority);

    @Override
    void close();
}
