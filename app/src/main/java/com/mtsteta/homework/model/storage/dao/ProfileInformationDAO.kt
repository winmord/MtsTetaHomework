package com.mtsteta.homework.model.storage.dao

import androidx.room.*
import com.mtsteta.homework.model.storage.entity.ProfileInformation

interface ProfileInformationDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProfileInformation(profileInformation: ProfileInformation)

    @Update
    fun updateProfileInformation(profileInformation: ProfileInformation)

    @Delete
    fun deleteProfileInformation(profileInformation: ProfileInformation)

    @Query("SELECT * FROM ProfileInformation WHERE email == :email")
    fun getProfileInformationByEmail(email: String): ProfileInformation

    @Query("SELECT * FROM ProfileInformation")
    fun getProfilesInformation(): List<ProfileInformation>
}