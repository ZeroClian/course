<template>
  <div>
    <p>
      <button @click="add()" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-edit"></i>
        新增
      </button>
      <button @click="list(1)" class="btn btn-white btn-default btn-round">
        <i class="ace-icon fa fa-refresh"></i>
        刷新
      </button>
    </p>

    <div id="form-modal" class="modal fade" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">新增</h4>
          </div>
          <div class="modal-body">
            <form class="form-horizontal">
              <div class="form-group">
                <label class="col-sm-2 control-label">名称</label>
                <div class="col-sm-10">
                  <input v-model="chapter.name" class="form-control" placeholder="名称">
                </div>
              </div>
              <div class="form-group">
                <label class="col-sm-2 control-label">课程</label>
                <div class="col-sm-10">
                  <input v-model="chapter.courseId" class="form-control" placeholder="课程">
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button type="button" class="btn btn-primary" @click="save()">保存</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <table id="simple-table" class="table  table-bordered table-hover">
      <thead>
      <tr>
        <th>ID</th>
        <th>名称</th>
        <th>课程</th>
        <th>操作</th>
      </tr>
      </thead>

      <tbody>
      <tr v-for="chapter in chapters">
        <td>{{ chapter.id }}</td>
        <td>{{ chapter.name }}</td>
        <td>{{ chapter.courseId }}</td>

        <td>
          <div class="hidden-sm hidden-xs btn-group">
            <button @click="edit(chapter)" class="btn btn-xs btn-info">
              <i class="ace-icon fa fa-pencil bigger-120"></i>
            </button>
            <button @click="del(chapter.id)" class="btn btn-xs btn-danger">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </button>
          </div>
        </td>
      </tr>
      </tbody>
    </table>
    <Pagination ref="pagination" v-bind:list="list"></Pagination>
  </div>
</template>

<script>
import Pagination from "../../components/pagination"

export default {
  components: {Pagination},
  name: "Chapter",
  data: function () {
    return {
      chapter: {
        name: '',
        courseId: ''
      },
      chapters: []
    }
  },
  mounted: function () {
    let _this = this
    _this.$refs.pagination.size
    _this.list(1)
  },
  methods: {
    add() {
      let _this = this;
      _this.chapter = {};
      $("#form-modal").modal("show");
    },
    edit(chapter) {
      let _this = this;
      _this.chapter = $.extend({}, chapter);
      $("#form-modal").modal("show");

    },
    list(page) {
      let _this = this
      Loading.show()
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/chapter/list', {
        page: page,
        size: _this.$refs.pagination.size
      }).then((respond) => {
        Loading.hide()
        _this.chapters = respond.data.content.list
        _this.$refs.pagination.render(page, respond.data.content.total)
      })
    },
    save() {
      let _this = this
      // 校验
      if (!Validator.require(_this.chapter.name, "名称") || !Validator.require(_this.chapter.courseId, "课程ID")) {
        return
      }
      _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/chapter/save', _this.chapter).then((respond) => {
        if (respond.data.success) {
          $("#form-modal").modal("hide");
          _this.list(1);
          Toast.success("保存成功!");
        } else {
          Toast.error(respond.data.message);
        }
      })
    },
    del(id) {
      let _this = this;
      Confirm.show("确认删除大章,删除后不可恢复?", function () {
        _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/chapter/delete/' + id).then((respond) => {
          if (respond.data.success) {
            _this.list(1);
            Toast.success("删除成功!")
          }
        })
      })
    }
  }
}
</script>

<style scoped>

</style>