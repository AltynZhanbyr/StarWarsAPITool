package com.example.starwarsapitool.domain.use_case

import com.example.starwarsapitool.data.dto.CharacterDto
import com.example.starwarsapitool.data.dto.toCharacter
import com.example.starwarsapitool.domain.model.Character
import com.example.starwarsapitool.domain.repository.RemoteStarWarsRepository
import com.example.starwarsapitool.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRemoteStarWarsCharacterUseCase @Inject constructor(
    private val remoteStarWarsRepository: RemoteStarWarsRepository
) {
    operator fun invoke(name:String): Flow<Resource<List<Character>>> = flow{
        try{
            emit(Resource.Loading())
            val characters:List<Character> = remoteStarWarsRepository.getCharacterByName(name).results.map {
                it.toCharacter()
            }
            emit(Resource.Success(characters))
        }catch (e:HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }catch (e:IOException){
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}