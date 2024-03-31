package androidx.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new com.v5foradnroid.userapp.DataBinderMapperImpl());
  }
}
